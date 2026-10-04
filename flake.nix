{
  description = "ElectraVia";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-25.05";

  outputs = { self, nixpkgs }:
    let
      systems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
      forAll = f: nixpkgs.lib.genAttrs systems (system: f nixpkgs.legacyPackages.${system});
    in {
      apps = forAll (pkgs: {
        default = {
          type = "app";
          program = toString (pkgs.writeShellScript "electravia" ''
            set -e
            saida=$(mktemp -d)
            ${pkgs.jdk21}/bin/javac -encoding UTF-8 -sourcepath src -d "$saida" src/Main.java
            ${pkgs.jdk21}/bin/java -cp "$saida" Main
          '');
        };
      });
    };
}