package nuc.rwenjie.common.config;

import com.aliyuncs.utils.StringUtils;
import nuc.rwenjie.common.config.jwt.JwtTokenUtil;
import nuc.rwenjie.common.config.security.DefaultUserDetailsService;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Author Rwenjie
 * @ClassName WebsocketConfig
 * @Description TODO websocket 配置
 * @Date 2021/6/1 15:49
 **/

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig  implements WebSocketMessageBrokerConfigurer {

    private String tokenHead= "Bearer";
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private DefaultUserDetailsService userDetailsService;

    /**
     * 添加这个Endpoints，这样在网页就可以通过websocket连接上服务
     * 也就是我们配置webSocket的服务地址，并且可以指定是否可以使用SocketJS
     * @param: registry
     * @return void
     **/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1. 将ws/ep路径注册为stomp的断点,用户连接了这个端点就可以进行websocket通讯，支持SocketJS
         * 2. setAllowedOrigins("*") 允许跨域
         * 3. withSockJS() 支持SocketJS访问
         **/
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 输入通道参数配置
     * @param: registration
     * @return void
     **/
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为链接，如果是需要获取token 并设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (StringUtils.isEmpty(token)) {
                       return null;
                    }
                    String authToken = token.substring(tokenHead.length());
                    String username = jwtTokenUtil.getUserNameFromToken(authToken);
                    System.out.println("username" + username);
                    //token 中存在用户名
                    if (StringUtils.isEmpty(username)) {
                       return null;
                    }
                    //登录 验证token是否有效
                    UserEntity user = userDetailsService.loadUserByUsername(username);
                    System.out.println(user);
                    //验证token是否有效，重新设置用户对象
                    if (jwtTokenUtil.validateToken(authToken, user)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        System.out.println("++++++++++++++++++++++++"+authenticationToken+"++++++++++++++++");
                        accessor.setUser(authenticationToken);
                    }
                }
                return message;
            }
        });

    }

    /**
     * 配置消息代理
     * @param: registry
     * @return void
     **/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域， 可以配置多个
        // 配置代理的目的地的前缀为、queue，可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}
