/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Check a login and a password following the requirement in the parameter
 * object.
 * Immutable class.
 * @author Grégory Van den Borre
 */
public final class SimpleAuthenticationChecker implements AuthenticationChecker {

    /**
     * Parameter rules.
     */
    private final AuthenticationRules parameters;

    /**
     * Create a new AuthenticationChecker from rules.
     * @param parameters List of rules to apply when authenticating, cannot be null.
     * @throws AssertionError if parameters is null.
     */
    public SimpleAuthenticationChecker(AuthenticationRules parameters) {
        assert parameters != null;
        this.parameters = parameters;
    }

    @Override
    public Credentials check(final String login, final String password) throws CredentialException {
        List<AuthenticationError> errors = new ArrayList<>();
        boolean loginValid = this.checkLogin(login, errors);
        boolean pwdValid = this.checkPassword(password, errors);
        if (loginValid && pwdValid) {
            return new Credentials(login, password);
        }
        throw new CredentialException(errors);
    }

    /**
     * Check the login against the given rules.
     *
     * @param login  Login to check.
     * @param errors List to store the errors.
     * @return True if the login is valid.
     * @throws AssertionError If errors is null.
     */
    private boolean checkLogin(final String login, final List<AuthenticationError> errors) {
        assert errors != null;
        boolean noError = true;
        if (login == null || login.isEmpty()) {
            errors.add(AuthenticationError.LOGIN_EMPTY);
            noError = false;
        } else if (login.length() < this.parameters.loginMinLength) {
            errors.add(AuthenticationError.LOGIN_TOO_SHORT);
            noError = false;
        } else if (login.length() > this.parameters.passMaxLength) {
            errors.add(AuthenticationError.LOGIN_TOO_LONG);
            noError = false;
        } else if (!Pattern.matches(this.parameters.loginPattern.pattern(), login)) {
            errors.add(AuthenticationError.INVALID_LOGIN_CHAR);
            noError = false;
        }
        return noError;
    }

    /**
     * Check the password against the given rules.
     *
     * @param password Password to check.
     * @param errors   List to store the errors.
     * @return True if password is valid.
     * @throws AssertionError If errors is null.
     */
    private boolean checkPassword(final String password, final List<AuthenticationError> errors) {
        assert errors != null;
        boolean noError = true;
        if(password == null || password.isEmpty()) {
            errors.add(AuthenticationError.PASS_EMPTY);
            noError = false;
        } else if (password.length() < this.parameters.passMinLength) {
            errors.add(AuthenticationError.PASS_TOO_SHORT);
            noError = false;
        } else if (password.length() > this.parameters.passMaxLength) {
            errors.add(AuthenticationError.PASS_TOO_LONG);
            noError = false;
        } else if (!Pattern.matches(this.parameters.passPattern.pattern(), password)) {
            errors.add(AuthenticationError.INVALID_PASS_CHAR);
            noError = false;
        }
        return noError;
    }

}
