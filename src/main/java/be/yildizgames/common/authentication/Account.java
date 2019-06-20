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

/**
 * Data representation for a user account.
 * @author Grégory Van den Borre
 */
public class Account {

    /**
     * User unique id.
     */
    private final String id;

    /**
     * User login.
     */
    private final String login;

    /**
     * User password.
     */
    private final String password;

    /**
     * User email.
     */
    private final String email;

    /**
     * User last connection time.
     */
    private final long lastConnectionDate;

    public Account(final String id, final String login, final String password, final String email, final long lastConnectionDate) {
        super();
        Objects.requireNonNull(id);
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);
        Objects.requireNonNull(email);
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.lastConnectionDate = lastConnectionDate;
    }

    public final Account resetPassword() {
        //FIXME do
        return this;
    }

    public final Account changePassword(final String newPassword) {
        //FIXME do
        return this;
    }

    public final Account changeEmail(String newEmail) {
        //FIXME do
        return this;
    }

    /**
     * Provide the user id.
     * @return User id.
     */
    public final String getId() {
        return this.id;
    }

    /**
     * Provide the user login.
     * @return User login.
     */
    public final String getLogin() {
        return this.login;
    }

    /**
     * Provide the user password.
     * @return User password.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Provide the user email.
     * @return User email.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Provide the user last connection time.
     * @return User last connection time.
     */
    public final long getLastConnectionDate() {
        return lastConnectionDate;
    }
}
