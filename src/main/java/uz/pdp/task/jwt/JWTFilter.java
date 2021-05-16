package uz.pdp.task.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.task.service.AuthDetailImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    final JWTProvider jwtProvider;
    final AuthDetailImpl authDetailImpl;

    public JWTFilter(JWTProvider jwtProvider, AuthDetailImpl authDetailImpl) {
        this.jwtProvider = jwtProvider;
        this.authDetailImpl = authDetailImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // REQUEST dan tokenni olish
        String token = httpServletRequest.getHeader("Authorization");

        // TOKEN borligini va boshlanishi Bearer bo'lishini tekshiryapmiz
        if (token != null && token.startsWith("Bearer")) {
            // Bearer dan keyingi tokenning uzi
            token = token.substring(7);

            // tokenni validatsiya qildik (token buzilmaganligini, muddati o'tmaganligini va h.k.)
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken) {
                // tokenni ichidan username ni oldik
                String usernameFromToken = jwtProvider.getUsernameFromToken(token);

                // username orqali userdetails ni oldik
                UserDetails userDetails = authDetailImpl.loadUserByUsername(usernameFromToken);

                // userdetails orqali atuhentication yaratdik
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                System.out.println(SecurityContextHolder.getContext().getAuthentication());

                // sistemaga kim kirganligini urnatdik
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
