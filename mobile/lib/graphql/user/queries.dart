const findUserGraphQL = """
query findUser {
  findUserById(id: 1) {
    id
    name
    email
    manager
    resident
    house {
      id
    }
  }
}
""";
