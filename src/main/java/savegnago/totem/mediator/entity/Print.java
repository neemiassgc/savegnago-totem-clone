package savegnago.totem.mediator.entity;

import java.util.List;

public final class Print {

	private Status status;
	private Result result;

	public Print() {}

	public Print (Status status, Result result) {
		this.status = status;
		this.result = result;
	}

	public Status getStatus () {
		return status;
	}

	public void setStatus (Status status) {
		this.status = status;
	}

	public Result getResult () {
		return result;
	}

	public void setResult (Result result) {
		this.result = result;
	}

	public static class Status {

		private boolean success;
		private List<String> error;
		private List<String> msg;

		private Status() {}

		public Status (boolean success, List<String> error, List<String> msg) {
			this.success = success;
			this.error = error;
			this.msg = msg;
		}

		public boolean isSuccess () {
			return success;
		}

		public void setSuccess (boolean success) {
			this.success = success;
		}

		public List<String> getError () {
			return error;
		}

		public void setError (List<String> error) {
			this.error = error;
		}

		public List<String> getMsg () {
			return msg;
		}

		public void setMsg (List<String> msg) {
			this.msg = msg;
		}
	}

	public static class Result {

		private String values;

		private Result() {}

		public Result (String values) {
			this.values = values;
		}

		public String getValues () {
			return values;
		}

		public void setValues (String values) {
			this.values = values;
		}
	}
}
