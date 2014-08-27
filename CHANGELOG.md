# Change log

## 0.6.1 ([#9](https://git.mobcastdev.com/Hermes/message-schemas/pull/9) 2014-08-27 15:18:37)

Change user to username from PasswordResetRequested

Improvement

This change is needed to accommodate for some SSO-related password-reset scenarios and as far as I know ExactTarget does not use any user information except from the email anyway.

## 0.6.0 ([#8](https://git.mobcastdev.com/Hermes/message-schemas/pull/8) 2014-08-19 11:28:42)

Added Email.Send message.

### New feature

* Added 'Email.Send' message as defined in schemas project.


## 0.5.1 ([#7](https://git.mobcastdev.com/Hermes/message-schemas/pull/7) 2014-08-14 15:35:06)

CP-1563: Added Epub ingestion schema case classes

improvement


## 0.5.0 ([#6](https://git.mobcastdev.com/Hermes/message-schemas/pull/6) 2014-08-14 11:16:23)

Added account suspended/closed and SSO details

### Breaking changes

- The `UserProfile` constructor now accepts an `AccountInfo` object
rather than an `acceptedTermsVersion` string.
- `AccountInfo` object has details including whether the account is
suspended or closed, plus an SSO user identifier.

## 0.4.0 ([#5](https://git.mobcastdev.com/Hermes/message-schemas/pull/5) 2014-08-11 11:49:45)

Modified the User type, and new password messages

### Breaking changes

- The `User` object no longer includes an `allowMarketingCommunication` flag. This has been moved into the new `UserProfile` object.
- Messages no longer inherit from an `Event` marker trait as it turned out to be useless.

### New features

- Now has a `UserProfile` object which encapsulates more detail about a user.
- Added `User.PasswordChanged` and `User.PasswordResetRequested` message types.

## 0.3.0 ([#4](https://git.mobcastdev.com/Hermes/message-schemas/pull/4) 2014-08-07 13:50:43)

Added User Credited event

### New features:

- Added `User.Credited` event for notifications when users are assigned credit to their account.


## 0.2.0 ([#3](https://git.mobcastdev.com/Hermes/message-schemas/pull/3) 2014-08-06 16:09:39)

Updated common-messaging version

### Breaking changes

- Updated common-messaging dependency to 1.0.0

## 0.1.1 ([#2](https://git.mobcastdev.com/Hermes/message-schemas/pull/2) 2014-08-01 17:35:55)

Auth events now match the schemas

### Improvements

- Auth events now match the JSON schemas
- Identifiers are declared using strongly typed types
- Event families now inherit a base trait to allow exhaustive pattern
matching

## 0.1.0 ([#1](https://git.mobcastdev.com/Hermes/message-schemas/pull/1) 2014-07-31 11:34:23)

Added schemas for auth events

### New features

- Now has strongly typed classes for auth events

