//
//  RoverPicRow.swift
//  iosApp
//
//  Created by Steve Malsam on 9/7/25.
//

import SwiftUI
import Shared

struct RoverPicRow: View {
    let photo: ModelPhoto
    
    var body: some View  {
        Text("Photo ID: \(photo.id) Sol: \(photo.sol)")
    }
}
