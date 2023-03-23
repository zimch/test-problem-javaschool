package com.digdes.school;

import java.util.*;

public class JavaSchoolStarter {

    private List<Map<String, Object>> table;
    private Long id;
    private String lastName;
    private Double cost;
    private Long age;
    private Boolean active;

    public JavaSchoolStarter() {
        this.table = new ArrayList<>();
        this.id = null;
        this.lastName = null;
        this.cost = null;
        this.age = null;
        this.active = null;
    }

    public List<Map<String, Object>> execute(String request) throws Exception {

        String[] afterSpliterator = doRequestSplit(request, " ");

        switch (detectOfControlCommand(afterSpliterator)) {
            case INSERT -> {
                /**
                 * Создаем строку с {@null} значениями
                 * Удаляем все пробелы в желаемых на добавление значениях
                 * Пробегаемся по каждому {@some}={@value}
                 * Меняем в {@workingMap} значения переданных ключей
                 */
                if (consistSomeCommand(afterSpliterator, SubControlRequest.VALUES.name())) {
                    Map<String, Object> workingMap = insertNullsAndReturn();
                    String[] goodLine = doRequestSplit(deleteSpaces(request.substring(14)), ",");
                    for (String s : goodLine) {
                        String[] keyAndValue = doRequestSplit(s, "=");
//                        System.out.println(keyAndValue[0].toLowerCase() + " " + keyAndValue[1]);
                        switch (keyAndValue[0].toLowerCase()) {
                            case "‘lastname’":
                                workingMap.put("lastname", keyAndValue[1]);
                                break;
                            case "‘id’":
                                workingMap.put("id", Long.valueOf(keyAndValue[1]));
                                break;
                            case "‘age’":
                                workingMap.put("age", Long.valueOf(keyAndValue[1]));
                                break;
                            case "’cost’":
                                workingMap.put("cost", Double.valueOf(keyAndValue[1]));
                                break;
                            case "‘active’":
                                workingMap.put("active", Boolean.valueOf(keyAndValue[1]));
                                break;
                        }
                    }

                    this.table.add(workingMap);
//                    System.out.println(goodLine);
                }
            }
            case SELECT -> {
                System.out.println("SELECT first");
            }
            case DELETE -> {
                System.out.println("DELETE first");
            }
            case UPDATE -> {
                System.out.println("UPDATE first");
            }
        }

        return table;
    }

    private String[] doRequestSplit(String request, String template) {
        return request.split(template);
    }

    private ControlRequests detectOfControlCommand(String[] firstCommand) {
        return switch (firstCommand[0]) {
            case "INSERT" -> ControlRequests.INSERT;
            case "DELETE" -> ControlRequests.DELETE;
            case "UPDATE" -> ControlRequests.UPDATE;
            case "SELECT" -> ControlRequests.SELECT;
            default -> null;
        };
    }

    private SubControlRequest detectOfSubControlCommands(String subCommand) {
        return switch (subCommand) {
            case "VALUES" -> SubControlRequest.VALUES;
            case "WHERE" -> SubControlRequest.WHERE;
            default -> null;
        };
    }

    private boolean consistSomeCommand(String[] splittedRequest, String command) {
        return Arrays.stream(splittedRequest)
                .anyMatch(t -> t.equals(command));
    }

    private String deleteSpaces(String lineWithSpaces) {
        return lineWithSpaces.replaceAll(" ", "");
    }

    private Map<String, Object> insertNullsAndReturn() {
        Map<String, Object> ans = new HashMap<>();
        ans.put("id", this.id);
        ans.put("lastname", this.lastName);
        ans.put("cost", this.cost);
        ans.put("age", this.age);
        ans.put("active", this.active);

        return ans;
    }

    public List<Map<String, Object>> getList() {
        return this.table;
    }

}
