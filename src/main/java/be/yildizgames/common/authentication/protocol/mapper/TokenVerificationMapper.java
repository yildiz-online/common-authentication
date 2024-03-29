/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.common.authentication.protocol.mapper;

import be.yildizgames.common.authentication.protocol.TokenVerification;
import be.yildizgames.common.authentication.protocol.mapper.exception.AuthenticationMappingException;
import be.yildizgames.common.mapping.BooleanMapper;
import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.common.mapping.model.PlayerIdMapper;

/**
 * @author Grégory Van den Borre
 */
public class TokenVerificationMapper implements ObjectMapper<TokenVerification> {

    private static final TokenVerificationMapper INSTANCE = new TokenVerificationMapper();

    private TokenVerificationMapper() {
        super();
    }

    public static TokenVerificationMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public TokenVerification from(String s) {
        try {
            String[] v = s.split(Separator.VAR_SEPARATOR);
            return new TokenVerification(PlayerIdMapper.getInstance().from(v[0]), BooleanMapper.getInstance().from(v[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new AuthenticationMappingException(e);
        }
    }

    @Override
    public String to(TokenVerification tokenVerification) {
        return PlayerIdMapper.getInstance().to(tokenVerification.userId())
                + Separator.VAR_SEPARATOR
                + BooleanMapper.getInstance().to(tokenVerification.authenticated());
    }
}
