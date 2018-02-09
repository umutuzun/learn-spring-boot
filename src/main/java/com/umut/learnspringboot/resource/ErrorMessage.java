package com.umut.learnspringboot.resource;

/**
 * Created by Umut Uzun on 2/9/2018.
 */
public class ErrorMessage {
        private final String errorMessage;

        public ErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
}
