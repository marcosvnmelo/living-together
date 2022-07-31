import 'package:flutter/material.dart';
import 'package:home/database/dao/expense_dao.dart';
import 'package:home/models/expense_model.dart';
import 'package:home/widgets/error_dialog.dart';

class ExpenseList extends StatelessWidget {
  final ExpenseDao _expenseDao = ExpenseDao();

  ExpenseList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Expenses'),
      ),
      body: FutureBuilder<List<Expense>>(
        future: _expenseDao.findAll(),
        initialData: const [],
        builder: (context, snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.none:
              break;

            case ConnectionState.waiting:
              return Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: const [
                    CircularProgressIndicator(),
                    SizedBox(height: 8),
                    Text('Loading'),
                  ],
                ),
              );

            case ConnectionState.active:
              break;

            case ConnectionState.done:
              final List<Expense> contacts = snapshot.data ?? [];

              return ListView.builder(
                itemBuilder: (itemBuilder, index) {
                  final Expense contact = contacts[index];
                  return _ExpenseItem(contact);
                },
                itemCount: contacts.length,
              );
            default:
          }

          return const ErrorDialog(
            message: 'Unexpected error',
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          // await Navigator.push(
          //   context,
          //   MaterialPageRoute(
          //     builder: (context) => const ContactForm(),
          //   ),
          // );
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}

class _ExpenseItem extends StatelessWidget {
  final Expense _expense;

  const _ExpenseItem(
    this._expense, {
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Card(
      child: ListTile(
        title: Text(
          _expense.name,
          style: const TextStyle(
            fontSize: 24,
          ),
        ),
        subtitle: Text(
          _expense.value.toString(),
          style: const TextStyle(
            fontSize: 16,
          ),
        ),
      ),
    );
  }
}
