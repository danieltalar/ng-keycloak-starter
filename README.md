# README #

This project contains starter Spring application connected with Keycloak server.

## Area to be explored in this project ##
* Keycloak server setup ready to run using `docker` and `docker-compose` tools
* Keycloak configuration for:
	* realm with RS256 using public/private key
	* identity providers (i.e. Google)
	* client/client scope
	* roles/groups
	* claims
	* create realm's Theme for Login, Account, Email
* Terraform script to create and configure Keycloak's realm with clients and roles
* SpringBoot and SpringSecurity microservice with:
	* public endpoints
	* endpoints secured using JWT access token and roles
	* endpoints that uses JWT claims in service layer
	* Keycloak connection for user's CRUD + changing user role
	* app configuration changable by environment variables (mutliple deployments with different params should be possible)
* Prepare docker image with SpringBoot application
* Explore authorization methods scenarios (login/password, using external providers)
* Explore scenarios with 2 or more applications connected with Keycloak's different clients
* Explore scenarios with claims
* Documentation for project

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact