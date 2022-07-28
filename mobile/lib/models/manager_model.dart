import 'resident_model.dart';

class Manager extends Resident {
  Manager({
    required String name,
    required String nickname,
    required String email,
    required String currentHouse,
    bool isManager = true,
  }) : super(
    name: name,
    nickname: nickname,
    email: email,
    currentHouse: currentHouse,
    isManager: isManager,
  );
}
