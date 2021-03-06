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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class UserIdTest {

    @Nested
    public class Constructor {

        @Test
        public void happyFlow() {
            UserId id = new UserId(15);
            Assertions.assertEquals(15, id.value);
        }

        @Test
        public void anonymous() {
            Assertions.assertEquals(-1, UserId.ANONYMOUS.value);
        }

        @Test
        public void zero() {
            UserId id = new UserId(0);
            Assertions.assertEquals(0, id.value);
        }

        @Test
        public void negative() {
            UserId id = new UserId(-5);
            Assertions.assertEquals(-5, id.value);
        }
    }

    @Nested
    public class HashCode {

        @Test
        public void same() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(5);
            Assertions.assertEquals(id.hashCode(), id2.hashCode());
        }

        @Test
        public void notSame() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(6);
            Assertions.assertNotEquals(id.hashCode(), id2.hashCode());
        }
    }

    @Nested
    public class Equals {

        @Test
        public void equal() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(5);
            Assertions.assertEquals(id, id2);
        }

        @Test
        public void notEqual() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(6);
            Assertions.assertNotEquals(id, id2);
        }

        @Test
        public void sameInstance() {
            UserId id = new UserId(5);
            Assertions.assertEquals(id, id);
        }

        @Test
        public void nullValue() {
            UserId id = new UserId(5);
            Assertions.assertNotEquals(id, null);
        }

        @Test
        public void differentType() {
            UserId id = new UserId(5);
            Assertions.assertNotEquals(id, 5);
        }
    }

}
