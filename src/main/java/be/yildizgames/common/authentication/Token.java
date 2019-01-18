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

import be.yildizgames.common.model.PlayerId;

import java.util.Arrays;

/**
 * A token contains information about a user authentication status.
 *
 * @author Grégory Van den Borre
 */
public final class Token {

    /**
     * User associated to the token.
     */
    private final PlayerId id;

    /**
     * Timestamp when the authentication occurred.
     */
    private final long authenticationTime;

    /**
     * Random key to ensure token authenticity.
     */
    private final int key;

    /**
     * Token authentication status.
     */
    private final Status status;

    private Token(PlayerId id, long authenticationTime, int key, Status status) {
        assert id != null;
        assert status != null;
        if(authenticationTime < 0) {
            throw new IllegalArgumentException("Time must be positive, value is " + authenticationTime);
        }
        this.id = id;
        this.authenticationTime = authenticationTime;
        this.key = key;
        this.status = status;
    }

    /**
     * Create a new token for a successfully authenticated user.
     *
     * @param id                 User authenticated.
     * @param authenticationTime Time when the authentication occurred.
     * @param key                Unique associated key.
     * @return A token with the user information, the authentication time, the authentication key and an authenticated status.
     */
    public static Token authenticated(final PlayerId id, final long authenticationTime, final int key) {
        return new Token(id, authenticationTime, key, Status.AUTHENTICATED);
    }

    /**
     * Create a new token for a not authenticated user.
     *
     * @return A token with no user information, no authentication time, no authentication key and a not authenticated status.
     */
    public static Token authenticationFailed() {
        return new Token(PlayerId.WORLD, 0, -1, Status.NOT_AUTHENTICATED);
    }

    /**
     * Create a new token for a banned user.
     *
     * @return A token with no user information, no authentication time, no authentication key and a banned status.
     */
    public static Token banned() {
        return new Token(PlayerId.WORLD, 0, -1, Status.BANNED);
    }

    /**
     * Create a new token for a not found user.
     *
     * @return A token with no user information, no authentication time, no authentication key and a not found status.
     */
    public static Token notFound() {
        return new Token(PlayerId.WORLD, 0, -1, Status.NOT_FOUND);
    }

    public static Token any(final PlayerId id, final int key, final Status status) {
        return new Token(id, 0, key, status);
    }

    /**
     * @return True if this token has authenticated status.
     */
    public boolean isAuthenticated() {
        return this.status == Status.AUTHENTICATED;
    }

    public PlayerId getId() {
        return id;
    }

    public long getAuthenticationTime() {
        return authenticationTime;
    }

    public int getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Token token = (Token) o;
        return key == token.key && id.equals(token.id) && status == token.status;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + key;
        result = 31 * result + status.hashCode();
        return result;
    }

    /**
     * Possible authentications status.
     *
     * @author Van den Borre Grégory
     */
    public enum Status {

        /**
         * Status when a user is successfully authenticated.
         */
        AUTHENTICATED(0),

        /**
         * Status when a user entered wrong credentials.
         */
        NOT_AUTHENTICATED(1),

        /**
         * Status when a user has been banned.
         */
        BANNED(2),

        /**
         * Status when the user is not found, or considered as not found(to avoid to send sensitive information to the client).
         */
        NOT_FOUND(3);

        public final int value;

        Status(int value) {
            this.value = value;
        }

        public static Status valueOf(int v) {
            return Arrays.stream(Status.values())
                    .filter(s -> s.value == v)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    @Override
    public String toString() {
        return this.getId() + ":" + this.getKey() + ":" + this.getStatus() + ":" + this.getAuthenticationTime();
    }
}
