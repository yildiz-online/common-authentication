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

import java.util.regex.Pattern;

/**
 * @author Grégory Van den Borre
 */
public class TemporaryAccount {

    private static final AuthenticationChecker CHECKER = new SimpleAuthenticationChecker(AuthenticationRules.DEFAULT);

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String login;

    private final String password;

    private final String email;

    private final String language;

    private TemporaryAccount(String login, String password, String email, String language) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.language = language;
    }

    /**
     * If the validation is successful, a TemporaryAccount will be created.
     * Otherwise, an AccountValidationException will be thrown.
     * @param login Login, cannot be null, and size must be withinb bounds.
     * @param password Password, cannot be null, and size must be withinb bounds.
     * @param email Email, cannot be null, and must match the email regular expression.
     * @return The created TemporaryAccount if the validation passes.
     */
    public static TemporaryAccount create(final String login, final String password, final String email, String language) {
        validate(login, password, email);
        return new TemporaryAccount(login, password, email, language);
    }

    private static void validate(String login, String password, String email) {
        try {
            CHECKER.check(login, password);
        } catch (CredentialException e) {
            throw new TemporaryAccountValidationException(e);
        }
        if(email == null) {
            throw new TemporaryAccountValidationException("email.mandatory");
        }
        if(!EMAIL_PATTERN.matcher(email).matches()) {
            throw new TemporaryAccountValidationException("email.invalid");
        }
    }

    public final String getLogin() {
        return this.login;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getLanguage() {
        return this.language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TemporaryAccount that = (TemporaryAccount) o;

        return login.equals(that.login) && password.equals(that.password) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return login + ":" + password + ":" + email;
    }
}
