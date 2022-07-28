class Expense {
  final int id;
  final String name;
  final String description;
  final double value;
  final DateTime date;

  Expense(
    this.id,
    this.name,
    this.description,
    this.value,
    this.date,
  );

  @override
  String toString() {
    return 'Expense(id: $id, name: $name, description: $description, value: $value, date: $date)';
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'description': description,
      'value': value,
      'date': date.toIso8601String(),
    };
  }
}
