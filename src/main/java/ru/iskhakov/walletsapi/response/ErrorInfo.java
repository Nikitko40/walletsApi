package ru.iskhakov.walletsapi.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorInfo {

    String value;
}