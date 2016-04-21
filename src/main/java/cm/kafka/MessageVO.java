package cm.kafka;

public class MessageVO {

	private String containername;
	private String imagename;
	private String imagetag = "latest";
	private PROCESS process;

	public String getContainername() {
		return containername;
	}

	public void setContainername(String containername) {
		this.containername = containername;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImagetag() {
		return imagetag;
	}

	public void setImagetag(String imagetag) {
		this.imagetag = imagetag;
	}

	public PROCESS getProcess() {
		return process;
	}

	public void setProcess(PROCESS process) {
		this.process = process;
	}

	@Override
	public String toString() {
		return "MessageVO [containername=" + containername + ", imagename="
				+ imagename + ", imagetag=" + imagetag + ", process=" + process
				+ "]";
	}

	
	
	
}
