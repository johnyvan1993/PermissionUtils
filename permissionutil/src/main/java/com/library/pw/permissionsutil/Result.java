package com.library.pw.permissionsutil;

/**
 * Created by android on 12/03/2018.
 */

public class Result {
    private String message;
    private String status;
    private String[] permissions;

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Result setStatus(String status) {
        this.status = status;
        return this;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public Result reset() {
        Result result = this;
        result.setMessage(null);
        result.setStatus(null);
        return result;
    }
}
