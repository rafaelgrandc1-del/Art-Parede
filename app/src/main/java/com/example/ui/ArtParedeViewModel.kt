package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArtParedeViewModel(private val repository: ArtParedeRepository) : ViewModel() {

    // Projects list
    val projects: StateFlow<List<MoodboardProject>> = repository.allProjects
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Filter selection for catalog
    private val _selectedFilter = MutableStateFlow("Todos")
    val selectedFilter: StateFlow<String> = _selectedFilter.asStateFlow()

    // Filter effects based on the selected category
    val filteredEffects: StateFlow<List<PaintEffect>> = _selectedFilter
        .map { filter ->
            val all = EffectCatalog.effects
            when (filter) {
                "Todos" -> all
                "Clássicos" -> all.filter { it.key in listOf("stucco_italiano", "marmorizado", "stucco_envelhecido") }
                "Concreto & Cimento" -> all.filter { it.key in listOf("concreto_ripado", "concreto_armado", "cimento_queimado") }
                "Rústico & Pedras" -> all.filter { it.key in listOf("travertino_romano") }
                "Metalizados" -> all.filter { it.key in listOf("aco_corten", "efeito_prata") }
                else -> all
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = EffectCatalog.effects
        )

    // Active project ID for moodboard details
    private val _selectedProjectId = MutableStateFlow<Int?>(null)
    val selectedProjectId: StateFlow<Int?> = _selectedProjectId.asStateFlow()

    private val _selectedProject = MutableStateFlow<MoodboardProject?>(null)
    val selectedProject: StateFlow<MoodboardProject?> = _selectedProject.asStateFlow()

    // Flow of moodboard items for active project
    @OptIn(ExperimentalCoroutinesApi::class)
    val activeProjectItems: StateFlow<List<MoodboardItem>> = _selectedProjectId
        .flatMapLatest { id ->
            if (id != null) {
                repository.getItemsForProject(id)
            } else {
                flowOf(emptyList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Interactive simulator current custom parameters
    private val _simColorHex = MutableStateFlow("#D4AF37")
    val simColorHex: StateFlow<String> = _simColorHex.asStateFlow()

    private val _simScale = MutableStateFlow(1.0f)
    val simScale: StateFlow<Float> = _simScale.asStateFlow()

    private val _simShine = MutableStateFlow(0.5f)
    val simShine: StateFlow<Float> = _simShine.asStateFlow()

    init {
        // Automatically seed some magnificent portfolio projects to show architects immediately
        viewModelScope.launch {
            repository.allProjects.first().let { currentList ->
                if (currentList.isEmpty()) {
                    seedInitialProjects()
                }
            }
        }
    }

    fun setFilter(filter: String) {
        _selectedFilter.value = filter
    }

    fun selectProject(projectId: Int) {
        _selectedProjectId.value = projectId
        viewModelScope.launch {
            _selectedProject.value = repository.getProjectById(projectId)
        }
    }

    fun clearProjectSelection() {
        _selectedProjectId.value = null
        _selectedProject.value = null
    }

    fun createProject(name: String, clientName: String, description: String) {
        viewModelScope.launch {
            repository.insertProject(
                MoodboardProject(
                    name = name,
                    clientName = clientName,
                    description = description
                )
            )
        }
    }

    fun deleteProject(project: MoodboardProject) {
        viewModelScope.launch {
            if (_selectedProjectId.value == project.id) {
                clearProjectSelection()
            }
            repository.deleteProject(project)
        }
    }

    fun addMoodboardItem(effectKey: String, notes: String) {
        val projectId = _selectedProjectId.value ?: return
        viewModelScope.launch {
            repository.insertItem(
                MoodboardItem(
                    projectId = projectId,
                    effectKey = effectKey,
                    customColorHex = _simColorHex.value,
                    customScale = _simScale.value,
                    shineIntensity = _simShine.value,
                    notes = notes
                )
            )
        }
    }

    fun deleteMoodboardItem(item: MoodboardItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun setSimColor(hex: String) {
        _simColorHex.value = hex
    }

    fun setSimScale(scale: Float) {
        _simScale.value = scale
    }

    fun setSimShine(shine: Float) {
        _simShine.value = shine
    }

    fun resetSim(defaultColorHex: String) {
        _simColorHex.value = defaultColorHex
        _simScale.value = 1.0f
        _simShine.value = 0.5f
    }

    private suspend fun seedInitialProjects() {
        // Project 1: Hermes Luxury Store
        val hId = repository.insertProject(
            MoodboardProject(
                name = "Hermes - Ala de Grife",
                clientName = "Boutique Hermes / Arq. Marina",
                description = "Painel principal e colunas estruturais para a nova boutique do Shopping Cidade Jardim. Foco em Marmorizado de alto luxo e texturas sóbrias com nuances de couro."
            )
        )
        repository.insertItem(
            MoodboardItem(
                projectId = hId.toInt(),
                effectKey = "marmorizado",
                customColorHex = "#FAF9F6", // Calacatta White
                customScale = 1.1f,
                shineIntensity = 1.0f,
                notes = "Marmorizado clássico de alto brilho com veios acinzentados orgânicos para o lounge principal."
            )
        )
        repository.insertItem(
            MoodboardItem(
                projectId = hId.toInt(),
                effectKey = "efeito_prata",
                customColorHex = "#E5E4E2", // Platinum
                customScale = 0.9f,
                shineIntensity = 0.8f,
                notes = "Efeito Prata Velvet com toque aveludado para as cabines de atendimento privativo."
            )
        )

        // Project 2: Casa Safra
        val sId = repository.insertProject(
            MoodboardProject(
                name = "Mansão Família Safra",
                clientName = "Família Safra / Arq. Roberto",
                description = "Living monumental com pé-direito duplo de 7 metros. Desafio de criar superfícies luxuosas, monolíticas, de toque sedoso e sem emendas aparentes."
            )
        )
        repository.insertItem(
            MoodboardItem(
                projectId = sId.toInt(),
                effectKey = "stucco_italiano",
                customColorHex = "#EADCC9", // Elegant Cream
                customScale = 1.0f,
                shineIntensity = 0.9f,
                notes = "Stucco Veneziano clássico polido aplicado com brilho vitrificado em toda a parede de fundo do living principal."
            )
        )
        repository.insertItem(
            MoodboardItem(
                projectId = sId.toInt(),
                effectKey = "travertino_romano",
                customColorHex = "#D3C2B0", // Roman Travertine
                customScale = 1.0f,
                shineIntensity = 0.1f,
                notes = "Travertino Romano aplicado no pórtico de entrada de pé-direito duplo."
            )
        )
    }
}

class ArtParedeViewModelFactory(private val repository: ArtParedeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtParedeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtParedeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
