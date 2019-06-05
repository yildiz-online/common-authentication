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

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * List of rules for the authentication.
 * The different rules contains the minimum and maximum size for the login and password and the patterns to match for the login and the password.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public final class AuthenticationRules {

    /**
     * Default authentication, login size between 3 and 20, password size
     * between 8 and 20, no special characters allowed for both.
     */
    public static final AuthenticationRules DEFAULT = new AuthenticationRules(20, 20, 3, 5, Pattern.compile("[a-zA-Z0-9]*"),
            Pattern.compile("[a-zA-Z0-9]*"));

    /**
     * Login maximum length.
     */
    public final int loginMaxLength;

    /**
     * Password maximum length.
     */
    public final int passMaxLength;

    /**
     * Login minimum length.
     */
    public final int loginMinLength;

    /**
     * Password minimum length.
     */
    public final int passMinLength;

    /**
     * Login accepted characters.
     */
    public final Pattern loginPattern;

    /**
     * Password accepted characters.
     */
    public final Pattern passPattern;

    /**
     * Create a new rule.
     * @param loginMaxLength Maximum size allowed for the login.
     * @param passMaxLength Maximum size allowed for the password.
     * @param loginMinLength Minimum size allowed for the login.
     * @param passMinLength Minimum size allowed for the password.
     * @param loginPattern Pattern to match for the login.
     * @param passPattern Pattern to match for the password.
     * @throws IllegalArgumentException <ul>
     *     <li>If loginMinLength is greater than loginMaxLength.</li>
     *     <li>If passMinLength is greater than passMaxLength.</li>
     *     <li>If loginMinLength or passMinLength is small than 0.</li>
     * </ul>
     * @throws AssertionError If loginPattern or passPattern is null.
     *
     */
    public AuthenticationRules(int loginMaxLength, int passMaxLength, int loginMinLength, int passMinLength, Pattern loginPattern, Pattern passPattern) {
        Objects.requireNonNull(loginPattern);
        Objects.requireNonNull(passPattern);
        if(loginMaxLength < loginMinLength) {
            throw new IllegalArgumentException("Login max value must be greater or equals to login min value.");
        }
        if(passMaxLength < passMinLength) {
            throw new IllegalArgumentException("Password max value must be greater or equals to password min value.");
        }
        if(loginMinLength < 0) {
            throw new IllegalArgumentException("Login min value must be greater or equals to 0.");
        }
        if(passMinLength < 0) {
            throw new IllegalArgumentException("Password min value must be greater or equals to 0.");
        }
        this.loginMaxLength = loginMaxLength;
        this.passMaxLength = passMaxLength;
        this.loginMinLength = loginMinLength;
        this.passMinLength = passMinLength;
        this.loginPattern = loginPattern;
        this.passPattern = passPattern;
    }
}
