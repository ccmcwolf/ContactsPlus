package apps.zambrone.com.contactsplus.model;

/**
 * Created by Chamith on 30/12/2016.
 */

public class Group {



    private String groupId;
    private String groupName;
    private String passCode;
    private boolean isOpen;
    private String groupIconPath;
    private String desription;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    private String adminId;


    public Group() {
    }


    public Group(String groupId, String groupName, String passCode, boolean isOpen, String groupIconPath, String desription, String adminId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.passCode = passCode;
        this.isOpen = isOpen;
        this.groupIconPath = groupIconPath;
        this.desription = desription;
        this.adminId = adminId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getGroupIconPath() {
        return groupIconPath;
    }

    public void setGroupIconPath(String groupIconPath) {
        this.groupIconPath = groupIconPath;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }
}
