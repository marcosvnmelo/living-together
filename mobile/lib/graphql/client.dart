import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

Future<GraphQLProvider> initGraphql(StatelessWidget myApp) async {
  final HttpLink httpLink = HttpLink(
    'http://10.0.2.2:8080/graphql/',
  );

  // final AuthLink authLink = AuthLink(
  //   getToken: () async => 'Bearer ${await getToken()}',
  // );

  final Link link = httpLink;

  ValueNotifier<GraphQLClient> client = ValueNotifier(
    GraphQLClient(
      link: link,
      cache: GraphQLCache(store: InMemoryStore()),
    ),
  );

  var app = GraphQLProvider(
    client: client,
    child: myApp,
  );

  return app;
}
