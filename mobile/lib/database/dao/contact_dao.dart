import 'package:home/database/app_database.dart';
import 'package:home/screens/contact_form.dart';
import 'package:sqflite/sqflite.dart';

class ContactDao {
  static const String _tableName = 'contacts';
  static const String tableSql = 'CREATE TABLE $_tableName('
      'id INTEGER PRIMARY KEY, '
      'name TEXT, '
      'account_number INTEGER)';

  Future<int> save(Contact contact) async {
    final Database db = await getDatabase();

    return await db.insert(
      _tableName,
      contact.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<List<Contact>> findAll() async {
    final Database db = await getDatabase();

    final List<Map<String, dynamic>> result = await db.query(_tableName);

    final List<Contact> contacts = [];

    for (var row in result) {
      contacts.add(
        Contact(
          row['id'],
          row['name'],
          row['account_number'],
        ),
      );
    }

    return contacts;
  }
}
