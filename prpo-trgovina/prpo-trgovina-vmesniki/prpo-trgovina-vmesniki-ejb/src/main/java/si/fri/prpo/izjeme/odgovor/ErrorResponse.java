package si.fri.prpo.izjeme.odgovor;

public class ErrorResponse {
	
	private int status;
	private String message;
	private int code;
	
	public ErrorResponse(int status, int code, String message){
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
