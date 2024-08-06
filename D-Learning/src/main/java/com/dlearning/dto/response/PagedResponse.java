package com.dlearning.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalPage;
    private long totalElements;
}
