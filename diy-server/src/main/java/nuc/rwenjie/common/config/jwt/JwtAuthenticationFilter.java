package nuc.rwenjie.common.config.jwt;

import nuc.rwenjie.common.config.security.DefaultUserDetailsService;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * JWT过滤器
 * 在访问受限URL前，验证JWT token
 * @author jitwxs
 * @since 2018/5/2 20:43
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private String tokenHeader = "Authorization";
    private String tokenHead= "Bearer";
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        //存在token
        if (null!=authHeader && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);

            //token用户存在用户名但是未登录
            if(null!=username && null==SecurityContextHolder.getContext().getAuthentication()) {
                //登录
                UserEntity user = userDetailsService.loadUserByUsername(username);
                //验证token是否有效
                if (jwtTokenUtil.validateToken(authToken, user)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}