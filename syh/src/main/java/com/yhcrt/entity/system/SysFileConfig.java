package com.yhcrt.entity.system;

public class SysFileConfig  implements java.io.Serializable{
    private String moduleCode;

    private String moduleDesc;

    private String backup;

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc == null ? null : moduleDesc.trim();
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup == null ? null : backup.trim();
    }

	@Override
	public String toString() {
		return "SysFileConfig [moduleCode=" + moduleCode + ", moduleDesc=" + moduleDesc + ", backup=" + backup + "]";
	}
}