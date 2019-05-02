package br.com.devdojo.error;

public class ValidationErrorDetails extends ErrorDetails {
    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public static final class ValidationErrorDetailsBuilder {
        private String title;
        private int status;
        private String details;
        private long timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private ValidationErrorDetailsBuilder() {
        }

        public static ValidationErrorDetailsBuilder newBuilder() {
            return new ValidationErrorDetailsBuilder();
        }

        public ValidationErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ValidationErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ValidationErrorDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ValidationErrorDetailsBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ValidationErrorDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ValidationErrorDetailsBuilder field(String field) {
            this.field = field;
            return this;
        }

        public ValidationErrorDetailsBuilder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setStatus(this.status);
            validationErrorDetails.setDetails(this.details);
            validationErrorDetails.setTimestamp(this.timestamp);
            validationErrorDetails.setTitle(this.title);
            validationErrorDetails.setDeveloperMessage(this.developerMessage);
            validationErrorDetails.setField(this.field);
            validationErrorDetails.setFieldMessage(this.fieldMessage);
            return validationErrorDetails;
        }
    }
}
