import 'package:home/database/dao/expense_dao.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

Future<Database> getDatabase() async {
  final dbPath = await getDatabasesPath();
  final String path = join(dbPath, 'living-together.db');

  return openDatabase(
    path,
    onCreate: (db, version) async {
      await db.execute(
        ExpenseDao.tableSql,
      );
    },
    version: 1,
  );
}
