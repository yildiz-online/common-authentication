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


package be.yildizgames.common.authentication;

/**
 * Type or errors for credential values.
 *
 * @author Grégory Van den Borre
 */
public enum AuthenticationError {

    /**
     * The login contains invalids characters.
     */
    INVALID_LOGIN_CHAR("connect.login.invalid"),

    /**
     * The password contains invalid characters.
     */
    INVALID_PASS_CHAR("connect.pwd.invalid"),

    /**
     * The login contains too many characters.
     */
    LOGIN_TOO_LONG("connect.login_long"),

    /**
     * The login does not contain enough characters.
     */
    LOGIN_TOO_SHORT("connect.login_short"),

    /**
     * The login is null or has 0 character.
     */
    LOGIN_EMPTY("connect.login_empty"),

    /**
     * The password contains too many characters.
     */
    PASS_TOO_LONG("connect.pwd_long"),

    /**
     * The password is null or has 0 character.
     */
    PASS_EMPTY("connect.pwd_empty"),

    /**
     * The password does not contain enough characters.
     */
    PASS_TOO_SHORT("connect.pwd_short");

    /**
     * Key associated to the error message.
     */
    public final String messageKey;

    /**
     * Initialize the enum value.
     *
     * @param messageKey Translation key associated to the error message.
     */
    //@Requires messageKey != null.
    AuthenticationError(final String messageKey) {
        this.messageKey = messageKey;
    }

}
