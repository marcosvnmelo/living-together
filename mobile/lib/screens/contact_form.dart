import 'package:flutter/material.dart';
import 'package:home/database/dao/contact_dao.dart';
import 'package:home/widgets/error_dialog.dart';

class ContactForm extends StatefulWidget {
  const ContactForm({Key? key}) : super(key: key);

  @override
  State<ContactForm> createState() => _ContactFormState();
}

class _ContactFormState extends State<ContactForm> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _accountNumberController =
      TextEditingController();
  final ContactDao _contactDao = ContactDao();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('New Contact'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _nameController,
              decoration: const InputDecoration(
                labelText: 'Full name',
              ),
              style: const TextStyle(
                fontSize: 24,
              ),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8.0),
              child: TextField(
                controller: _accountNumberController,
                decoration: const InputDecoration(
                  labelText: 'Account number',
                ),
                style: const TextStyle(
                  fontSize: 24,
                ),
                keyboardType: TextInputType.number,
              ),
            ),
            SizedBox(
              width: double.maxFinite,
              child: ElevatedButton(
                onPressed: () async {
                  final String name = _nameController.text;
                  final int? accountNumber =
                      int.tryParse(_accountNumberController.text);

                  if (name.isEmpty || accountNumber == null) {
                    showDialog(
                      context: context,
                      builder: (context) => const ErrorDialog(
                        message: 'All fields are required',
                      ),
                    );
                    return;
                  }

                  await _contactDao.save(Contact(0, name, accountNumber));

                  if (!mounted) return;

                  Navigator.pop(context);
                },
                style: Theme.of(context).elevatedButtonTheme.style,
                child: const Text('Create'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class Contact {
  final int id;
  final String name;
  final int accountNumber;

  const Contact(this.id, this.name, this.accountNumber);

  @override
  String toString() {
    return 'Contact(name: $name, accountNumber: $accountNumber)';
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'account_number': accountNumber,
    };
  }
}
