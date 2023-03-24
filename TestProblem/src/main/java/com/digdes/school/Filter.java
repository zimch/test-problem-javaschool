package com.digdes.school;

import java.util.Map;

public interface Filter {
    Map<String, Object> doFilter(String strForFilter);
}
