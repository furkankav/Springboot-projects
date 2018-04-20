package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer  // Enables an authorization server
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	//defines the id of the client application that is authorized to authenticate, 
	//the client application provides this in order to be allowed to send request to the server.
	@Value("${security.jwt.client-id}")
	private String clientId;
	//is the client application’s password.
	@Value("${security.jwt.client-secret}")
	private String clientSecret;
	//In a non-trivial implementation client ids and passwords will be securely stored in a 
	//database and retrievable through a separate API that clients applications access during deployment.
	
	//we define grant type password here because it’s not enabled by default
	@Value("${security.jwt.grant-type}")
	private String grantType;
	//read, write defines the level of access we are allowing to resources
	@Value("${security.jwt.scope-read}")
	private String scopeRead;
	
	@Value("${security.jwt.scope-write}")
	private String scopeWrite = "write";
	//The resource Id specified here must be specified on the resource server as well
	@Value("${security.jwt.resource-ids}")
	private String resourceIds;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer
		        .inMemory()
		        .withClient(clientId)
		        .secret(clientSecret)
		        .authorizedGrantTypes(grantType)
		        .scopes(scopeRead, scopeWrite)
		        .resourceIds(resourceIds);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager)
			.accessTokenConverter(accessTokenConverter)
			.tokenStore(tokenStore);
	}

}
