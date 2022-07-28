import 'person_model.dart';

class Resident extends Person {
  Resident({
    required String name,
    required String nickname,
    required String email,
    required String currentHouse,
    bool isManager = false,
  }) : super(
    name: name,
    nickname: nickname,
    email: email,
    currentHouse: currentHouse,
    isManager: isManager,
  );
}
