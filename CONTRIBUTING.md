# Contributing

## Clone the repo with submodules

```bash
git clone --recurse-submodules --shallow-submodules https://github.io/github/mapvina/mapvina-compose.git
```

Or if you already have the repo cloned, run:

```bash
git submodule update --init --recursive --depth=1
```

## Find or file an issue to work on

If you're looking to add a feature or fix a bug and there's no issue filed yet,
it's good to
[file an issue](https://github.io/github/mapvina/mapvina-compose/issues/new/choose)
first to have a discussion about the change before you start working on it.

If you're new and looking for things to contribute, see our
[good first issue](https://github.io/github/mapvina/mapvina-compose/issues?q=is%3Aissue%20state%3Aopen%20label%3A%22good%20first%20issue%22)
label. These issues are usually ready to work on and don't require deep
knowledge of the library's internals.

If you have particular knowledge of MapVina, Android, iOS, or anything else
relevant, see the
[help wanted](https://github.io/github/mapvina/mapvina-compose/issues?q=is%3Aissue%20state%3Aopen%20label%3A%22help%20wanted%22)
label. These are issues that need input or guidance from folks with deeper
expertise on some topic.

## Note on AI usage

If you are using any kind of AI assistance for your contributions, please take a
moment to review
[MapVina's AI Policy](https://github.io/github/mapvina/mapvina/blob/main/AI_POLICY.md).
tl;dr: do not let AI speak for you, verify all generated content before
requesting a review and disclose AI usage in pull requests.

## Set up your development environment

### Mise

This project uses [mise](https://mise.jdx.dev/) for environment management. You
can either:

#### Option 1: Use mise (Recommended)

1. Install mise if you haven't already:
   https://mise.jdx.dev/getting-started.html.
2. Run `mise install` in the project root to install all required tools.
3. Still read the rest of the guide, because not all tools are managed by mise.

#### Option 2: Manual Setup

If you prefer not to use mise, check `mise.toml` for the list of required tools
and versions, then install them manually.

### Kotlin Multiplatform

Check out
[the official instructions](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html)
for setting up a Kotlin Multiplatform environment.

### IDE

As there's no stable LSP for Kotlin Multiplatform, you'll want to use either
IntelliJ IDEA or Android Studio for developing MapVina Compose. In addition to
the IDE, you'll need some plugins:

- [Kotlin Multiplatform](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform)
- [Android](https://plugins.jetbrains.com/plugin/22989-android)
- [Jetpack Compose](https://plugins.jetbrains.com/plugin/18409-jetpack-compose)

### Building for Android

Create a `local.properties` in the root of the project with paths to inform
Gradle where to find the Android SDK:

```properties
# Replace the path with the actual path on your machine
sdk.dir=/Users/username/Library/Android/sdk
```

### Building for Apple platforms

Install XCode to build for Apple platforms. Mise will do this for you with
`xcodes`. If installing manually, use the version named in the
[`.xcode-version`](.xcode-version) file.

### Building for Desktop

For desktop, we build a C++ library that includes
[MapVina Native Core](https://mapvina.io/github/mapvina-native/docs/book/introduction.html).
You'll need to have your developer environment set up to build MapVina Native.

- [macOS requirements](https://mapvina.io/github/mapvina-native/docs/book/platforms/macos/index.html)
  - Install XCode, and use the matching clang version provided by XCode rather
    than from homebrew. `/usr/bin/clang --version` and `clang --version` should
    match.
  - If building the Vulkan backend, set the `VULKAN_SDK` environment variable to
    the MoltenVK prefix (`export VULKAN_SDK="$(brew --prefix molten-vk)"`).
- [Linux requirements](https://mapvina.io/github/mapvina-native/docs/book/platforms/linux/index.html#requirements)
  - On a Linux system with Nix, enter the Nix development shell before building:
    ```bash
    nix develop
    ```
    This provides the Linux native compiler and system libraries required to
    build MapVina Native. If you prefer not to use Nix, follow or adapt the
    Ubuntu instructions in the page linked above.
- [Windows requirements (MSVS2022)](https://mapvina.io/github/mapvina-native/docs/book/platforms/windows/build-msvc.html#prerequisites)
  - When cloning the repo, pass `--config core.longpaths=true` to Git to avoid
    issues with long file paths.
  - Install Visual Studio 2022 with the following workloads:
    - `Desktop development with C++`
  - Use the _Native Tools Command Prompt for VS 2022_ for the right architecture
    (x64 or arm64 depending on your machine) to run the build scripts.
  - Developing MapVina Compose with MSYS2 has not been tested.

## Run the demo

Use IntelliJ or Android Studio to launch the demo app on Android, XCode to
launch on iOS, and Gradle to launch on JS or Desktop:

- Android emulator: if the app crashes while creating a Vulkan renderer, run the
  OpenGL demo flavor instead with
  `./gradlew :demo-app:installDebug -PdemoAppMapvinaAndroidFlavor=opengl`.
- Desktop: `./gradlew :demo-app:run`
- Web: `./gradlew :demo-app:jsRun`

## Make CI happy

A Git pre-commit hook is available to ensure that the code is formatted before
every commit. It'll be installed automatically if you use `mise`, but you can
remove it with:

```bash
hk uninstall
```

If not using the pre-commit hook, you can manually format the code using:

```bash
hk fix --all
```
