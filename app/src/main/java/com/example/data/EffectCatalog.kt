package com.example.data

import com.example.R

data class PaintEffect(
    val key: String,
    val title: String,
    val category: String,
    val description: String,
    val imageRes: Int,
    val properties: List<String>,
    val idealFor: String,
    val clientStory: String,
    val defaultColorHex: String,
    val suggestedColors: List<String>
)

object EffectCatalog {
    val effects = listOf(
        PaintEffect(
            key = "stucco_italiano",
            title = "Stucco Italiano (Marmorino)",
            category = "Clássico Veneziano",
            description = "O Stucco Italiano (Marmorino) é um acabamento mineral polido de brilho profundo e textura sedosa que remete aos palácios renascentistas de Veneza. Composto por cal apagada e pó de mármore selecionado, oferece uma superfície vitrificada e sofisticada com veios artísticos sutis.",
            imageRes = R.drawable.img_stucco_italiano_1784248517330,
            properties = listOf(
                "Brilho espelhado profundo (95%)",
                "Textura lisa e toque aveludado",
                "Alta respirabilidade e durabilidade mineral"
            ),
            idealFor = "Halls monumentais, lavabos elegantes, lareiras e paredes de destaque.",
            clientStory = "Utilizado no living principal da Mansão Safra, conferindo imponência atemporal e recepção monumental.",
            defaultColorHex = "#EADCC9", // Cream Ivory
            suggestedColors = listOf("#EADCC9", "#DFD5C6", "#2D2A26", "#8F8171", "#B1A18F")
        ),
        PaintEffect(
            key = "marmorizado",
            title = "Marmorizado Artístico",
            category = "Pedra Nobre",
            description = "Uma recriação hiper-realista das pedras de mármore mais nobres do mundo, como Calacatta Gold e Nero Marquina. Cada veio é meticulosamente pintado à mão com pincéis artísticos, seguido por camadas de verniz protetor e polimento vitrificado.",
            imageRes = R.drawable.img_stucco_italiano_1784248517330, // shared marbled image
            properties = listOf(
                "Polimento de alto brilho espelhado (100%)",
                "Veios tridimensionais orgânicos e únicos",
                "Impermeável e de facílima higienização"
            ),
            idealFor = "Colunas, pórticos de entrada, nichos de adegas e painéis principais.",
            clientStory = "Destaque nas paredes de destaque da nova boutique Hermes, harmonizando com a sofisticação do couro clássico.",
            defaultColorHex = "#FAF9F6", // Calacatta White
            suggestedColors = listOf("#FAF9F6", "#1C1B1A", "#E6D7C3", "#5C564F", "#D4AF37")
        ),
        PaintEffect(
            key = "travertino_romano",
            title = "Mármore Travertino Romano",
            category = "Sedimentar Texturizado",
            description = "Uma reprodução fiel da rocha calcária clássica de Roma. Apresenta furos e ranhuras horizontais orgânicas que remetem à erosão natural de milênios. Toque rústico, mineral e de elegância sóbria.",
            imageRes = R.drawable.img_travertino_romano_1784248526895,
            properties = listOf(
                "Visual fosco natural texturizado",
                "Porosidade simulada com fidelidade tátil",
                "Excelente isolamento acústico aparente"
            ),
            idealFor = "Fachadas internas, pé-direito duplo, lareiras e ambientes minimalistas.",
            clientStory = "Escolha unânime para o pórtico de entrada de uma galeria de arte nos Jardins, dialogando perfeitamente com iluminação zenital.",
            defaultColorHex = "#D3C2B0", // Roman Stone
            suggestedColors = listOf("#D3C2B0", "#EBE0D0", "#C8B6A4", "#9E8E7D", "#60564D")
        ),
        PaintEffect(
            key = "stucco_envelhecido",
            title = "Stucco Envelhecido",
            category = "Pátina Histórica",
            description = "Inspirado nas românticas vilas da Toscana, este efeito traz o clássico Stucco Italiano com uma sutil pátina dourada-sépia e cera de abelha envelhecida. O acabamento evoca história, aconchego e uma pátina nobre de passagem do tempo.",
            imageRes = R.drawable.img_travertino_romano_1784248526895, // sharing rich travertine look
            properties = listOf(
                "Brilho acetinado suave e quente",
                "Manchas orgânicas em degradê sépia",
                "Camada protetora de ceras naturais de abelha"
            ),
            idealFor = "Adegas, bibliotecas, salas de jantar aconchegantes e lavabos intimistas.",
            clientStory = "Criado para harmonizar com painéis de madeira de demolição na residência de campo de um renomado arquiteto.",
            defaultColorHex = "#C3B091", // Aged Ochre
            suggestedColors = listOf("#C3B091", "#AA9676", "#D3C29D", "#7A6855", "#4E3F30")
        ),
        PaintEffect(
            key = "aco_corten",
            title = "Aço Corten Oxidado",
            category = "Industrial de Grife",
            description = "O efeito Aço Corten reproduz com perfeição a oxidação natural do metal. Com tonalidades quentes variando do laranja ferrugem ao marrom-bronze e terra de siena, confere um aspecto contemporâneo e dramático com reflexos metálicos sutis.",
            imageRes = R.drawable.img_aco_corten_1784248536607,
            properties = listOf(
                "Nuances de oxidação realistas (ferrugem artística)",
                "Toque texturizado sem áspero excessivo",
                "Reflexos metálicos sob incidência de luz direta"
            ),
            idealFor = "Home theaters modernos, escritórios executivos, halls de elevador e fachadas.",
            clientStory = "Destaque na sede de um dos principais escritórios de advocacia de alto padrão na Av. Faria Lima.",
            defaultColorHex = "#8B4513", // Rust
            suggestedColors = listOf("#8B4513", "#5C2E0B", "#D2691E", "#3A2110", "#A0522D")
        ),
        PaintEffect(
            key = "concreto_ripado",
            title = "Concreto Ripado Brutalista",
            category = "Arquitetônico Moderno",
            description = "Sucesso absoluto na arquitetura moderna paulistana, simula com extrema precisão o concreto moldado em fôrmas de tábuas de madeira. Exibe nós, veios de madeira impressos e juntas de dilatação perfeitas.",
            imageRes = R.drawable.img_concreto_ripado_1784248544880,
            properties = listOf(
                "Textura de réguas/ripas de madeira em alto-relevo",
                "Tom cinza cimento com nuances realistas",
                "Estética contemporânea escandinava ou brutalista"
            ),
            idealFor = "Painéis de TV de grandes dimensões, escadarias e varandas gourmet.",
            clientStory = "Aplicado na CASACOR em ambiente integrado vencedor de melhor arquitetura de interiores.",
            defaultColorHex = "#A9A9A9", // Cement Gray
            suggestedColors = listOf("#A9A9A9", "#BEBEBE", "#808080", "#555555", "#DCDCDC")
        ),
        PaintEffect(
            key = "concreto_armado",
            title = "Concreto Armado Aparente",
            category = "Arquitetônico Minimalista",
            description = "A representação minimalista e refinada do concreto aparente polido. Possui acabamento de toque sedoso e variação de nuances suaves cinza-claras, com marcas sutis de juntas e bolhas naturais de ar.",
            imageRes = R.drawable.img_concreto_ripado_1784248544880, // concrete share
            properties = listOf(
                "Toque perfeitamente liso e aveludado",
                "Nuances de cinzas industriais limpos",
                "Impermeabilizado com resina fosca de proteção"
            ),
            idealFor = "Halls minimalistas, cozinhas integradas, lofts modernos e garagens de luxo.",
            clientStory = "Especificado para um triplex contemporâneo de um conhecido colecionador de carros esportivos nos Jardins.",
            defaultColorHex = "#C0C0C0", // Light Concrete
            suggestedColors = listOf("#C0C0C0", "#D3D3D3", "#909090", "#707070", "#E0E0E0")
        ),
        PaintEffect(
            key = "efeito_prata",
            title = "Efeito Prata Velvet",
            category = "Metalizado Luxo",
            description = "Um acabamento luxuoso de toque aveludado com brilho perolado prata iridescente. Sob iluminação direcionada de focos de LED, suas microesferas refletem claro-escuro dinâmico, simulando tecido de seda nas paredes.",
            imageRes = R.drawable.img_efeito_prata_1784248553470,
            properties = listOf(
                "Toque macio que lembra veludo e camurça",
                "Reflexos metálicos iridescentes com movimento",
                "Acabamento que valoriza e amplia a iluminação do espaço"
            ),
            idealFor = "Cabeceiras de suítes master, closets sofisticados e salas de estar finas.",
            clientStory = "Embelezamento das salas VIP de atendimento de uma prestigiada grife de relógios suíços.",
            defaultColorHex = "#E5E4E2", // Platinum Silver
            suggestedColors = listOf("#E5E4E2", "#B0C4DE", "#C0C0C0", "#E6E6FA", "#D3D3D3")
        ),
        PaintEffect(
            key = "cimento_queimado",
            title = "Cimento Queimado Clássico",
            category = "Urbano Moderno",
            description = "O queridinho da decoração contemporânea. Nosso cimento queimado é feito com polímeros e microcimento de altíssima resistência, oferecendo um manchado orgânico (nuance) uniforme que traz elegância despojada sem fissuras ou trincas.",
            imageRes = R.drawable.img_efeito_prata_1784248553470, // concrete metalic blend
            properties = listOf(
                "Sem emendas ou juntas de dilatação necessárias",
                "Manchado suave contínuo e equilibrado",
                "Fórmula exclusiva que não descasca e não racha"
            ),
            idealFor = "Integrações totais de ambientes, lofts, lavabos e áreas gourmet integradas.",
            clientStory = "Revestimento unificador de uma cobertura de 600m² no Itaim Bibi, cobrindo paredes e colunas estruturais.",
            defaultColorHex = "#8E8E8E", // Slate Gray
            suggestedColors = listOf("#8E8E8E", "#708090", "#778899", "#4682B4", "#B0C4DE")
        )
    )

    fun getEffect(key: String): PaintEffect? {
        return effects.find { it.key == key }
    }
}
