package com.myApp.connection.builders;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlTable {
    private List<String> commands;

    public static SqlTableBuilder builder() {
        return new SqlTableBuilder();
    }

    public SqlTable(SqlTableBuilder sqlTableBuilder) {
        this.commands = sqlTableBuilder.commands;
    }

    public String command() {
        StringBuilder sb = new StringBuilder();
        sb.append(commands.get(0) + " ( ");
        sb.append(commands.subList(1, commands.size()).stream().collect(Collectors.joining(", ")));
        sb.append(" ) ");
        return sb.toString().toLowerCase();
    }

    public static class SqlTableBuilder {
        private List<String> commands = new ArrayList<>();

        public SqlTableBuilder table(String name) {
            if (!commands.isEmpty()) {
                return this;
            }
            commands.add(MessageFormat.format("create table if not exists {0} ", name));
            return this;
        }

        public SqlTableBuilder primaryKey(String columnName) {
            if (commands.size() != 1) {
                return this;
            }
            commands.add(MessageFormat.format("{0} integer primary key autoincrement", columnName));
            return this;
        }

        public SqlTableBuilder intColumn(String name, String options) {
            if (commands.size() < 2) {
                return this;
            }
            commands.add(MessageFormat.format("{0} integer {1} ", name, options));
            return this;
        }

        public SqlTableBuilder stringColumn(String name, int length, String options) {
            if (commands.size() < 2) {
                return this;
            }
            commands.add(MessageFormat.format("{0} varchar({1}) {2} ", name, length, options));
            return this;
        }

        public SqlTableBuilder decimalColumn(String name, int scale, int precision, String options) {
            if (commands.size() < 2) {
                return this;
            }
            commands.add(MessageFormat.format("{0} decimal({1}, {2}) {3} ", name, scale, precision, options));
            return this;
        }

        public SqlTableBuilder column(String name, String type, String options) {
            if (commands.size() < 2) {
                return this;
            }
            commands.add(MessageFormat.format("{0} {1} {2} ", name, type, options));
            return this;
        }

        public SqlTableBuilder foreignKey(String columnName, String foreignTable, String foreignColumn, String options) {
            if (commands.size() < 2) {
                return this;
            }
            commands.add(MessageFormat.format("foreign key ({0}) references {1}({2}) {3}", columnName, foreignTable, foreignColumn, options));
            return this;
        }

        public SqlTable build() {
            return new SqlTable(this);
        }
    }
}
