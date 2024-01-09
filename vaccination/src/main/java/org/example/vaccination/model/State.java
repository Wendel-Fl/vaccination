package org.example.vaccination.model;

import lombok.Getter;

@Getter
public enum State {
    AM("Amazonas", "AM", "Manaus"),
    AL("Alagoas", "AL", "Maceió"),
    AC("Acre", "AC", "Rio Branco"),
    AP("Amapá", "AP", "Macapá"),
    BA("Bahia", "BA", "Salvador"),
    CE("Ceará", "CE", "Fortaleza"),
    ES("Espírito Santo", "ES", "Vitória"),
    GO("Goiás", "GO", "Goiânia"),
    MA("Maranhão", "MA", "São Luís"),
    MG("Minas Gerais", "MG", "Belo Horizonte"),
    MS("Mato Grosso do Sul", "MS", "Campo Grande"),
    MT("Mato Grosso", "MT", "Cuiabá"),
    PA("Pará", "PA", "Belém"),
    PB("Paraíba", "PB", "João Pessoa"),
    PE("Pernambuco", "PE", "Recife"),
    PI("Piauí", "PI", "Teresina"),
    PR("Paraná", "PR", "Curitiba"),
    RJ("Rio de Janeiro", "RJ", "Rio de Janeiro"),
    RN("Rio Grande do Norte", "RN", "Natal"),
    RO("Rondônia", "RO", "Porto Velho"),
    RR("Roraima", "RR", "Boa Vista"),
    RS("Rio Grande do Sul", "RS", "Porto Alegre"),
    SE("Sergipe", "SE", "Aracaju"),
    SC("Santa Catarina", "SC", "Florianópolis"),
    SP("São Paulo", "SP", "São Paulo"),
    TO("Tocantins", "TO", "Palmas"),
    DF("Distrito Federal", "DF", "Brasília");

    private final String name;
    private final String acronym;
    private final String capital;

    /**
     * Construtor do enum
     *
     * @param name    nome da unidade da federação completo
     * @param acronym   sigla da unidade da federação
     * @param capital nome da capital da unidade da federação
     */
    State(final String name, final String acronym, final String capital) {
        this.name = name;
        this.acronym = acronym;
        this.capital = capital;
    }

    /**
     * Converte a partir do nome da Unidade da Federacao, para o respectivo enum.
     *
     * @param nameUf o nome da Unidade da Federação. Exemplo: "São Paulo"
     * @return o enum da Unidade da Federação
     * @throws IllegalArgumentException caso não ache o enum pelo nome da UF
     */
    public static State fromFU(final String nameUf) {
        for (final State fu : State.values()) {
            if (fu.name.equalsIgnoreCase(nameUf)) {
                return fu;
            }
        }

        throw new IllegalArgumentException(nameUf);
    }

    /**
     * Converte a partir da Sigla da UF no parâmetro, para o enum da Unidade da Federação.
     *
     * @param acronym da Unidade da Federação. Exemplo: "MG"
     * @return a Unidade da Federação
     * @throws IllegalArgumentException caso a acronym da UF não exista
     */
    public static State fromAcronym(final String acronym) {
        for (final State uf : State.values()) {
            if (uf.acronym.equalsIgnoreCase(acronym)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(acronym);
    }

    /**
     * Converte, a partir do nome da capital da UF, para o Enum.
     *
     * @param capital da Unidade da Federação. Exemplo: "Porto Alegre"
     * @return a Unidade da Federacao com a capital passada no parâmetro
     * @throws IllegalArgumentException caso o nome da capital não exista
     */
    public static State fromCapital(final String capital) {
        for (final State uf : State.values()) {
            if (uf.capital.equalsIgnoreCase(capital)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(capital);
    }

    @Override
    public String toString() {
        return "Estado{" + "nome='" + name + '\'' +
                ", sigla='" + acronym + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}
