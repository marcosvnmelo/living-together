import 'package:home/database/app_database.dart';
import 'package:home/models/expense_model.dart';
import 'package:sqflite/sqflite.dart';

class ExpenseDao {
  static const String _tableName = 'expenses';
  static const String tableSql = 'CREATE TABLE $_tableName('
      'id INTEGER PRIMARY KEY, '
      'name TEXT, '
      'description TEXT, '
      'value REAL, '
      'date INTEGER)';

  Future<int> save(Expense expense) async {
    final Database db = await getDatabase();

    return await db.insert(
      _tableName,
      expense.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<List<Expense>> findAll() async {
    final Database db = await getDatabase();

    final List<Map<String, dynamic>> result = await db.query(_tableName);

    final List<Expense> expenses = [];

    for (var row in result) {
      expenses.add(
        Expense(
          row['id'],
          row['name'],
          row['description'],
          row['value'],
          row['date'],
        ),
      );
    }

    return expenses;
  }

  Future<void> removeAll() async {
    final Database db = await getDatabase();

    await db.delete(_tableName, where: '1 = 1');
  }
}
