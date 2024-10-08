import Keycloak from 'keycloak-js';

const keycloakConfig = {
  realm: 'demo',
  url: 'http://localhost:8090/auth',
  clientId: 'demo_client',
};

const keycloak = Keycloak(keycloakConfig);

export default keycloak;
