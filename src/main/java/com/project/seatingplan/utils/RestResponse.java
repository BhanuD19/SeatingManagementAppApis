package com.project.seatingplan.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RestResponse implements Map<String, Object> {
  public static final String KEY_STATUS = "status";
  public static final String KEY_MESSAGE = "message";
  public static final String KEY_DATA = "data";
  public static final String KEY_TOTAL_RECORDS = "totalRecords";
  public static final String KEY_TOTAL_PAGES = "totalPages";
  public static final String KEY_PAGE_SIZE = "pageSize";
  public static final String KEY_PAGE_NUMBER = "pageNo";
  public static final String KEY_CURRENT_DATETIME = "currentDateTime";
  public static final String STATUS_ERROR = "error";
  public static final String STATUS_SUCCESS = "success";
  public static final String STATUS_FAIL = "fail";

  private Map<String, Object> response = new LinkedHashMap<>();

  private RestResponse() {
  }

  public static RestResponse build() { return new RestResponse(); }

  public RestResponse add(String name, Object value) {
    this.response.put(name, value);
    return this;
  }


  /**
   * Sets this response as error, setting the status and message key.
   *
   * @param error message
   * @return the rest response
   */
  public RestResponse withError(String error) { return add(KEY_STATUS, STATUS_ERROR).add(KEY_MESSAGE, error); }

  /**
   * Sets this response as success, setting the status and message key.
   *
   * @param message the message
   * @return the rest response
   */
  public RestResponse withSuccess(String message) {
    return add(KEY_STATUS, STATUS_SUCCESS).add(KEY_MESSAGE, message);
  }

  /**
   * Sets this response as success.
   *
   * @return the rest response
   */
  public RestResponse withSuccess() {
    return add(KEY_STATUS, STATUS_SUCCESS);
  }

  /**
   * Sets this response as fail.
   *
   * @return the rest response
   */
  public RestResponse withFail() {
    return add(KEY_STATUS, STATUS_FAIL);
  }

  /**
   * Creates a success response with data.
   *
   * @param message the message
   * @param data    the data
   * @return the rest response
   */
  public RestResponse withSuccess(String message, Object data) {
    return withSuccess(message, KEY_DATA, data);
  }

  /**
   * Creates a success response with custom data.
   *
   * @param message the message
   * @param name    the name
   * @param value   the value
   * @return the rest response
   */
  public RestResponse withSuccess(String message, String name, Object value) {
    return withSuccess(message).add(name, value);
  }

  /**
   * Sets object as 'data' value.
   *
   * @param data the data
   * @return the rest response
   */
  public RestResponse withData(Object data) {
    return add(KEY_DATA, data);
  }

  /**
   * Creates a success response with custom data
   *
   * @param message
   * @param name
   * @param value
   * @return
   */
  public RestResponse withData(Long totalPages, Long totalSize, Integer pageNumber, String key, Object value) {
    if (!response.containsKey(KEY_DATA)) {
      add(KEY_TOTAL_RECORDS, totalSize).add(KEY_TOTAL_PAGES, totalPages).add(KEY_PAGE_NUMBER, pageNumber)
        // .add(KEY_DATA, new LinkedHashMap<String, Object>());
        .add(KEY_DATA, value);
    }
    return this;
  }

  /**
   * With data.
   *
   * @param key   the key
   * @param value the value
   * @return the rest response
   */
  @SuppressWarnings("unchecked")
  public RestResponse withData(String key, Object value) {
    if (!response.containsKey(KEY_DATA)) {
      add(KEY_DATA, new LinkedHashMap<String, Object>());
    }
    ((Map<String, Object>) response.get(KEY_DATA)).put(key, value);
    return this;
  }

  /**
   * Factory method for success.
   *
   * @param message the message
   * @param name    the name
   * @param data    the data
   * @return the rest response
   */
  public static RestResponse success(String message, String name, Object data) {
    return new RestResponse().withSuccess(message).add(name, data);
  }

  /**
   * Factory method for error response.
   *
   * @param errorMessage the error message
   * @return the rest response
   */
  public static RestResponse error(String errorMessage) {
    return new RestResponse().withError(errorMessage);
  }

  /**
   * Factory method for error response using the exception message.
   *
   * @param e the exception
   * @return the rest response
   */
  public static RestResponse error(Exception e) {
    return new RestResponse().withError(e.getMessage());
  }



  @Override
  public int size() {
    return response.size();
  }

  @Override
  public boolean isEmpty() {
    return response.isEmpty();
  }

  @Override
  public boolean containsKey(Object o) {
    return response.containsKey(o);
  }

  @Override
  public boolean containsValue(Object o) {
    return response.containsValue(o);
  }

  @Override
  public Object get(Object o) {
    return response.get(o);
  }

  @Override
  public Object put(String s, Object o) {
    return response.put(s, o);
  }

  @Override
  public Object remove(Object o) {
    return response.remove(o);
  }

  @Override
  public void putAll(Map<? extends String, ? extends Object> map) {
    response.putAll(map);
  }

  @Override
  public void clear() {
    response.clear();
  }

  @Override
  public Set<String> keySet() {
    return response.keySet();
  }

  @Override
  public Collection<Object> values() {
    return response.values();
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    return response.entrySet();
  }
}
