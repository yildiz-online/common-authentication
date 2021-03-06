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

package be.yildizgames.common.authentication.protocol;

/**
 * @author Grégory Van den Borre
 */
public class TemporaryAccountCreationResultDto {

    private boolean emailMissing;
    private boolean emailInvalid;
    private boolean accountExisting;
    private boolean emailExisting;
    private boolean invalidLogin;
    private boolean invalidPassword;
    private boolean technicalIssue;

    public TemporaryAccountCreationResultDto() {
        super();
    }

    public static TemporaryAccountCreationResultDto success() {
        return new TemporaryAccountCreationResultDto();
    }

    public void setEmailMissing(boolean missing) {
        this.emailMissing = missing;
    }

    public void setEmailInvalid(boolean invalid) {
        this.emailInvalid = invalid;
    }

    public void setAccountExisting(boolean existing) {
        this.accountExisting = existing;
    }

    public void setEmailExisting(boolean existing) {
        this.emailExisting = existing;
    }

    public void setInvalidLogin(boolean invalid) {
        this.invalidLogin = invalid;
    }

    public void setInvalidPassword(boolean invalid) {
        this.invalidPassword = invalid;
    }

    public void setTechnicalIssue(boolean issue) {
        this.technicalIssue = issue;
    }

    public boolean isEmailMissing() {
        return emailMissing;
    }

    public boolean isInvalidEmail() {
        return emailInvalid;
    }

    public boolean isAccountExisting() {
        return accountExisting;
    }

    public boolean isEmailExisting() {
        return emailExisting;
    }

    public boolean isInvalidLogin() {
        return invalidLogin;
    }

    public boolean isInvalidPassword() {
        return invalidPassword;
    }

    public boolean isTechnicalIssue() {
        return technicalIssue;
    }

    public boolean hasError() {
        return this.isAccountExisting()
                || this.isInvalidLogin()
                || this.isInvalidPassword()
                || this.isInvalidEmail()
                || this.isTechnicalIssue()
                || this.isEmailMissing()
                || this.isEmailExisting();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TemporaryAccountCreationResultDto that = (TemporaryAccountCreationResultDto) o;

        return emailMissing == that.emailMissing && emailInvalid == that.emailInvalid && accountExisting == that.accountExisting && emailExisting == that.emailExisting && invalidLogin == that.invalidLogin && invalidPassword == that.invalidPassword && technicalIssue == that.technicalIssue;
    }

    @Override
    public int hashCode() {
        int result = (emailMissing ? 1 : 0);
        result = 31 * result + (emailInvalid ? 1 : 0);
        result = 31 * result + (accountExisting ? 1 : 0);
        result = 31 * result + (emailExisting ? 1 : 0);
        result = 31 * result + (invalidLogin ? 1 : 0);
        result = 31 * result + (invalidPassword ? 1 : 0);
        result = 31 * result + (technicalIssue ? 1 : 0);
        return result;
    }
}
