package apps.zambrone.com.contactsplus.Handlers;

/**
 * Created by Chamith on 17/10/2016.
 */
public class ExceptionMessageClass {
    static ErrorMessageShow errorMessageShow;

    public static ErrorMessageShow getFirebaseErrorMessage(Exception e) {
        errorMessageShow = new ErrorMessageShow();
        switch (e.getMessage()) {
            case "The custom token format is incorrect. Please check the documentation.":
                errorMessageShow.setMessage("Token format is incorrect.");
                errorMessageShow.setType(4);
                break;
            case "The custom token corresponds to a different audience.":
                errorMessageShow.setMessage("Token format audience error.");
                errorMessageShow.setType(4);
                break;
            case "The supplied auth credential is malformed or has expired.":
                errorMessageShow.setMessage("Credential is malformed or has expired.");
                errorMessageShow.setType(3);
                break;
            case "The email address is badly formatted.":
                errorMessageShow.setMessage("Please provide proper email");
                errorMessageShow.setType(1);
                break;
            case "The password is invalid or the user does not have a password.":
                errorMessageShow.setMessage("password is Invalid.");
                errorMessageShow.setType(2);
                break;
            case "This operation is sensitive and requires recent authentication. Log in again before retrying this request.":
                errorMessageShow.setMessage("This operation is sensitive and requires recent authentication.");
                errorMessageShow.setType(4);
                break;
            case "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address. ":
                errorMessageShow.setMessage("An account already exists with the same email address.");
                errorMessageShow.setType(1);
                break;
            case "The email address is already in use by another account.":
                errorMessageShow.setMessage("Welcome back! You are already our member!");
                errorMessageShow.setType(4);
                break;
            case "This credential is already associated with a different user account.":
                errorMessageShow.setMessage("Already associated with a different user account");
                errorMessageShow.setType(4);
                break;
            case "The user account has been disabled by an administrator.":
                errorMessageShow.setMessage("Account is disabled by admin. please contact us");
                errorMessageShow.setType(5);
                break;
            case "There is no user record corresponding to this identifier. The user may have been deleted.":
                errorMessageShow.setMessage("Hi, Please sign up again! we missed you!");
                errorMessageShow.setType(5);
                break;
            case "The user\\'s credential is no longer valid. The user must sign in again.":
                errorMessageShow.setMessage("Hey! We have noticed change with your activity Please sign in again");
                errorMessageShow.setType(5);
                break;
            case "This operation is not allowed. You must enable this service in the console.":
                errorMessageShow.setMessage("Token format is incorrect.");
                errorMessageShow.setType(1);
                break;
            case "The given password is invalid.":
                errorMessageShow.setMessage("Password is invalid.");
                errorMessageShow.setType(2);
                break;
            default:
                errorMessageShow.setMessage("Token format is incorrect.");
                errorMessageShow.setType(4);
                break;
        }
        return errorMessageShow;

//        if(e.getMessage() == "ERROR_INVALID_CUSTOM_TOKEN") {
//            message = "The custom token format is incorrect. Please check the documentation.";
//        }if(e.getMessage() == "ERROR_CUSTOM_TOKEN_MISMATCH") {
//            message = "The custom token corresponds to a different audience.";
//        }if(){}
//            ("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
//            ("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
//            ("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
//            ("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
//            ("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
//            ("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
//            ("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
//            ("ERROR_CREDENTIAL_ALREADY_IN_USE", "This credential is already associated with a different user account."));
//            ("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
//            ("ERROR_USER_TOKEN_EXPIRED", "The user\'s credential is no longer valid. The user must sign in again."));
//            ("ERROR_USER_NOT_FOUND", "There is no user record corresponding to this identifier. The user may have been deleted."));
//            ("ERROR_INVALID_USER_TOKEN", "The user\'s credential is no longer valid. The user must sign in again."));
//            ("ERROR_OPERATION_NOT_ALLOWED", "This operation is not allowed. You must enable this service in the console."));
//            ("ERROR_WEAK_PASSWORD", "The given password is invalid."));
//        }
//    }
    }

}
