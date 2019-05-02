package br.com.devdojo.error;

public class ResourceNotFoundDetails extends ErrorDetails {

    public static final class ResourceNotFoundDetailsBuilder {
        private String title;
        private int status;
        private String details;
        private long timestamp;
        private String developerMessage;

        private ResourceNotFoundDetailsBuilder() {
        }

        public static ResourceNotFoundDetailsBuilder newBuilder() {
            return new ResourceNotFoundDetailsBuilder();
        }

        public ResourceNotFoundDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ResourceNotFoundDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ResourceNotFoundDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ResourceNotFoundDetailsBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ResourceNotFoundDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setStatus(status);
            resourceNotFoundDetails.setDetails(details);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            return resourceNotFoundDetails;
        }
    }
}
