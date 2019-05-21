package com.pengzhaopeng.springbootjsp.config;

import com.pengzhaopeng.springbootjsp.utils.MyPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 权限认证
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        // 定制请求的授权规则
        // 首页所有人都可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
                // level1/**请求只能是登陆系统，有VIP1角色的人
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");
        //开启自动配置的登陆功能，如果没有权限就跳转到登陆页面。
        // 定制登陆页面：.loginPage("/userlogin");跳转到自定义的登录页
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        /**
         * spring security自动处理这些请求
         * 默认登录页：
         * 1、/login 登陆
         * 2、重定向到/login?error表示登陆失败
         * 3、更多详细规则……
         * 4、默认post形式的/login代表处理登陆
         * 定制登录页：（请求往哪发送也需要定制）
         * 5、一旦定制以后，本案例默认post形式的/userlogin代表处理登陆。（也可以.loginProcessingUrl()定义往哪儿发送登陆请求）
         */

        //开启自动配置的注销功能。注销成功返回首页
        http.logout().logoutSuccessUrl("/");
        /**
         * 1、访问/logout 表示用户注销，清空session
         * 2、注销成功后返回： /login?logout
         */

        // 开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        /**
         * 登陆成功以后，将cookie发给浏览器保存，以后访问页面会带上这个cookie，只要通过检查就可以免登陆
         * 点击注销会删除cookie
         * .rememberMeParameter("remember")：定制页面的“记住我”
         */
    }

    // 定义认证规则。这里是从内存中获取，实际可以从数据库中获取。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        // 定义用户
        auth.inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("wdf").password("123456").roles("VIP1","VIP2")
                .and()
                .withUser("panda").password("123456").roles("VIP2","VIP3")
                .and()
                .withUser("apo").password("123456").roles("VIP1","VIP3");
    }
}

