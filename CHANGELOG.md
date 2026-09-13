# Changelog

## [Unreleased]

### Fixed

- Restored distinct Go colors for packages, shadowing variables, methods, structs, built-ins, types, receivers, and callable values.
- Replaced the low-contrast editor search-result background with a readable dark state.
- Restored strikeout-only styling for deleted text.
- Consolidated duplicate gray, green, orange, cyan, and UI-state shades.

## [3.0.0] - 2026-09-13

### Changed

- Raised the minimum supported IntelliJ Platform build to `262`.
- Switched release builds to Java 25 and removed the upper IDE build limit.
- Added compatibility verification against the latest available IntelliJ IDEA 2026.3 EAP build.

## [2.0.0] - 2026-09-13

### Added

- Dedicated highlighting for current Go language color keys.
- Accessible semantic colors for banners, notifications, Git states, diffs, and breakpoints.
- Automated compatibility verification for the first and last supported IntelliJ Platform branches.

### Changed

- Updated the Apricode palette to the current brand green, pink, cyan, and orange colors.
- Migrated the build to IntelliJ Platform Gradle Plugin 2.x and Gradle 9.7.1.
- Cleaned and normalized the Apricode Build, Run, and Debug icons.
- Replaced template documentation and Marketplace copy with Apricode Monokai documentation.

### Removed

- Removed the unused JetBrains template tool window, listeners, services, tests, and Kotlin runtime.
- Removed the deprecated `DynamicBundle(String)` API usage.
- Removed obsolete Qodana, Kover, and UI test robot configuration.

## [1.2.2] - 2024-09-19

### Added

- Support for new IDE versions.

## [1.2.1] - 2024-09-03

### Added

- Support for new IDE versions.

## [1.2.0] - 2024-04-05

### Fixed

- Color and icon sizing issues.

## [1.1.4] - 2024-03-28

### Added

- Support for IntelliJ Platform 2024.1.

## [1.1.3] - 2023-12-07

### Added

- Support for IntelliJ Platform 2023.3.

## [1.1.2] - 2023-04-05

### Added

- Support for IntelliJ Platform 2023.1.

## [1.1.1] - 2022-12-16

### Changed

- Updated injected language and Blade interpolation highlighting.

## [1.1.0] - 2022-12-07

### Added

- Support for IntelliJ Platform 2022.3.

## [1.0.0] - 2021-11-28

- Initial release.

[Unreleased]: https://github.com/arm092/apricode-monokai/compare/v3.0.0...HEAD
[3.0.0]: https://github.com/arm092/apricode-monokai/compare/v2.0.0...v3.0.0
[2.0.0]: https://github.com/arm092/apricode-monokai/compare/1.1.1...v2.0.0
[1.2.2]: https://github.com/arm092/apricode-monokai/compare/1.2.1...1.2.2
[1.2.1]: https://github.com/arm092/apricode-monokai/compare/1.2.0...1.2.1
[1.2.0]: https://github.com/arm092/apricode-monokai/compare/1.1.4...1.2.0
