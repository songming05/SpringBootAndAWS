package com.sb.beFriendWith.config.auth;

import com.sb.beFriendWith.config.auth.dto.OAuthAttributes;
import com.sb.beFriendWith.config.auth.dto.SessionUser;
import com.sb.beFriendWith.domain.user.User;
import com.sb.beFriendWith.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegator = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegator.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(oAuthAttributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                oAuthAttributes.getAttributes(),
                oAuthAttributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes oAuthAttributes) {
        User user = userRepository.findByEmail(oAuthAttributes.getEmail())
                .map(entity -> entity.update(oAuthAttributes.getName(), oAuthAttributes.getPicture()))
                .orElse(oAuthAttributes.toEntity());
        return userRepository.save(user);
    }
}
