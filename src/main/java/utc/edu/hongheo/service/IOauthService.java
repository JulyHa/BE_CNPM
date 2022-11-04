package utc.edu.hongheo.service;

import utc.edu.hongheo.dto.TokenDto;
import utc.edu.hongheo.model.User;


public interface IOauthService {
    TokenDto getTokenFacebook(TokenDto tokenDto);

}
