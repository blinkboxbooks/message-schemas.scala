# Change log

## 0.8.0 ([#15](https://git.mobcastdev.com/Hermes/message-schemas/pull/15) 2015-01-16 17:43:21)

Book.Cover and Scala 2.11

### Breaking change

- Added Book.Cover message
- Removed the previous outdated references of epub message as book
- Upgraded from Scala 2.10 to 2.11

## 0.7.3 ([#14](https://git.mobcastdev.com/Hermes/message-schemas/pull/14) 2014-11-27 18:22:34)

Include File Pending schema

### Improvements

- Schema to support the Watcher

## 0.7.2 ([#13](https://git.mobcastdev.com/Hermes/message-schemas/pull/13) 2014-10-22 11:52:27)

Removed intermediate case class for gift details

### Patch
* Removed intermediate case class to avoid having to nest objects.

## 0.7.1 ([#12](https://git.mobcastdev.com/Hermes/message-schemas/pull/12) 2014-10-08 13:51:07)

Added schemas for gift redeemed messages

patch 

- Added schema for Gift.Redeemed message

## 0.7.0 ([#11](https://git.mobcastdev.com/Hermes/message-schemas/pull/11) 2014-09-25 14:16:14)

CP-1864 Introduce messages for user migration to SSO

### New feature

Introduce messages to be used when an user have been migrated to SSO by either matching all his passwords (Total), some of them (Partial) or by resetting his password (Reset).

## 0.6.2 ([#10](https://git.mobcastdev.com/Hermes/message-schemas/pull/10) 2014-09-04 15:34:56)

Introduce cross-compilation for 2.11.2

### Improvements

* Introduce cross-compilation for Scala 2.11.2

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

