import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    var body: some View {
        listView()
    }
    
    private func listView() -> AnyView{
        switch viewModel.photos {
        case .loading:
            return AnyView(Text("Loading..."))
            
        case .result(let photoList):
            return AnyView(List(photoList) { photo in
                RoverPicRow(photo: photo)
            })
        }
    }
}

extension ContentView {
    enum LoadablePhotos {
        case loading
        case result([ModelPhoto])
    }
    
    @MainActor
    class ViewModel: ObservableObject {
        @Published var photos = LoadablePhotos.loading
        
        let helper: KoinHelper = KoinHelper()
        
        init() {
            self.loadPhotos(sol: 1, page: 1)
        }
        
        private func loadPhotos(sol: Int, page: Int) {
            Task {
                do {
                    self.photos = .loading
                    let photos = try await helper.getPhotos(sol: Int32(sol), page: Int32(page))
                    self.photos = .result(photos)
                }
            }
        }
    }
}

extension ModelPhoto: Identifiable {}
