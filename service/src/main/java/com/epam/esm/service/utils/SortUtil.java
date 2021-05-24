package com.epam.esm.service.utils;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortUtil {
    private static final String PARAMETER_SORT = "sort";
    private static final char MINUS = '-';
    /**
     * Method returns Sort, generated from group of parameters
     *
     * @param groupParameters group of parameters
     * @return the Sort
     */
    public static Sort buildSort(Map<String, List<String>> groupParameters) {
        List<String> listParamSort=null;
        if (groupParameters.get(PARAMETER_SORT) != null) {
            listParamSort = groupParameters.get(PARAMETER_SORT);
        }
        return (listParamSort != null) ? sortFromList(listParamSort) : Sort.unsorted();
    }

    private static Sort sortFromList(List<String> listParamSort) {
        List<Sort.Order> sortAsc = listParamSort.stream().filter(s -> s.charAt(0) != MINUS).map(s -> Sort.Order.asc(s)).collect(Collectors.toList());
        List<Sort.Order> sortDesc = listParamSort.stream().filter(s -> s.charAt(0) == MINUS).map(s -> Sort.Order.desc(s.substring(1))).collect(Collectors.toList());
        List<Sort.Order> orders = (sortAsc != null) ? sortAsc : null;
        if (orders != null && (sortDesc != null)) {
            orders.addAll(sortDesc);
        }
        if (orders == null && (sortDesc != null)) {
            orders = sortDesc;
        }
        return (orders != null) ? Sort.by(sortAsc) : Sort.unsorted();
    }
}
